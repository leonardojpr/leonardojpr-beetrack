package com.leonardojpr.db_generator;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;
/**
 * Created by Leonardojpr on 06/07/18.
 *
 * Clase para definir las tablas de la base de datos.
 */
public class TableDefinition {

    /**
     * @param schema
     * @return Entity
     */
    public static Entity addArticle(Schema schema) {
        Entity article = schema.addEntity("Article");
        article.implementsSerializable();
        article.setCodeBeforeClass("@SuppressWarnings(\"ALL\")");
        article.addIdProperty().notNull().autoincrement().unique().primaryKey();
        article.addStringProperty("ids");
        article.addStringProperty("name");
        article.addStringProperty("title");
        article.addStringProperty("author");
        article.addStringProperty("publishedAt");
        article.addStringProperty("description");
        article.addStringProperty("url");
        article.addStringProperty("urlToImage");
        article.addBooleanProperty("isFavorite");
        return article;
    }
}
