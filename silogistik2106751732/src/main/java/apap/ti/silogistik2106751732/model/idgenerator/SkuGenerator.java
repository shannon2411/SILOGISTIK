package apap.ti.silogistik2106751732.model.idgenerator;

import apap.ti.silogistik2106751732.model.Barang;
import com.sun.jdi.LongType;
import jakarta.annotation.PostConstruct;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;

import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.mapping.Any;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.AnyType;
import org.hibernate.type.Type;
import org.hibernate.type.descriptor.java.IntegerJavaType;
import org.hibernate.type.descriptor.java.LongJavaType;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.hibernate.type.descriptor.jdbc.LongVarcharJdbcType;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;

import java.lang.*;

public class SkuGenerator implements IdentifierGenerator, Configurable {
    @Override
    public Serializable generate(
            SharedSessionContractImplementor session, Object obj)
            throws HibernateException {
        int tipeBarang = ((Barang) obj).getTipeBarang();
        String prefix;
        switch (tipeBarang) {
            case 1:
                prefix = "ELEC";
                break;
            case 2:
                prefix = "CLOT";
                break;
            case 3:
                prefix = "FOOD";
                break;
            case 4:
                prefix = "COSM";
                break;
            case 5:
                prefix = "TOOL";
                break;
            default:
                prefix = "";
                break;
        }

        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        Stream<String> ids = session.createQuery(query).stream();

        Long max = ids.map(o -> {
                    return (o.substring(4));
                }).mapToLong(Long::parseLong)
                .max()
                .orElse(0L);
        return String.format("%s%03d", prefix, (max + 1));
    }

//    @Override
//    public void configure(Type type, Properties properties,
//                          ServiceRegistry serviceRegistry) throws MappingException {
//        prefix = properties.getProperty("prefix");
//    }
}