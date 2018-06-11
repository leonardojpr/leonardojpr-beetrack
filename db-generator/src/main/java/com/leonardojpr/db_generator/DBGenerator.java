package com.leonardojpr.db_generator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Schema;
/**
 * Created by Leonardojpr on 06/07/18.
 *
 * Clase principal, se encarga de generar las tablas definidas en la clase TableDefinition.
 */
public class DBGenerator {
    private static final String DEFAULT_JAVA_PACKAGE = "com.pruebatecnica.leonardojpr_beetrack.database";
    private static final String OUT_DIR = "../app/src/main/java";

    public static void main(String args[]) throws Exception {

        Schema schema = new Schema(1, DEFAULT_JAVA_PACKAGE);
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(schema, OUT_DIR);
    }

    private static void addTables(Schema schema) {
        TableDefinition.addArticle(schema);
    }

}
