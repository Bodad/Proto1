package business;

import Data.Order;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@ApplicationScoped
@Traced
@Timed
public class SystemGateway {

    @Inject
    MongoClient mongoClient;

    private static final Logger LOG = Logger.getLogger(SystemGateway.class);


    public void updateEnumCollections() {
        updateEnumCollection(Order.Type.class);
    }

    public <E extends Enum<E>> void updateEnumCollection(Class<E> enumClass) {
        String dbName = "LC_Gateway";
        String collectionName = enumClass.getName().replace("$", ".");

        MongoDatabase systemGatewayDB = mongoClient.getDatabase(dbName);
        MongoCollection<Document> collection = systemGatewayDB.getCollection(collectionName);

        if (collection.countDocuments() > 0) {
            collection.drop();
        }

        List<Document> documentList = Stream.of(enumClass.getEnumConstants())
                                            .map(this::getDocumentFromEnumConstant)
                                            .collect(Collectors.toList());

        collection.insertMany(documentList);
    }

    private <E extends Enum<E>> Document getDocumentFromEnumConstant(E e) {
        Document document = new Document();
        document.append("constant", e.name());

        Stream.of(e.getClass().getDeclaredFields())
                                           .filter(this::onlyPublicNonStaticFields)
                                           .map(f -> processField(f, e))
                                           .forEach(ef -> document.append(ef.fieldName, ef.fieldJson));


        return document;
    }

    private boolean onlyPublicNonStaticFields(Field field) {
        int modifiers = field.getModifiers();
        return (Modifier.isStatic(modifiers) == false)
                && (Modifier.isPublic(modifiers) == true);
    }

    private <E extends Enum<E>> EnumField processField(Field field, E e) {
        String fieldName = field.getName();
        Class<?> fieldType = field.getType();
        String fieldTypeName = fieldType.getName();
        String fieldJson = getField(field, e).toString();

        return new EnumField(fieldName, fieldTypeName, fieldJson);
    }

    private <E extends Enum<E>> Object getField(Field field, E enumInstance) {
        Object result = null;
        try {
            result = field.get(enumInstance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to get enumeration field value", e);
        }
        return result;
    }

}
