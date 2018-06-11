package com.pruebatecnica.leonardojpr_beetrack.util;

import com.pruebatecnica.leonardojpr_beetrack.app.App;
import com.pruebatecnica.leonardojpr_beetrack.database.Article;
import com.pruebatecnica.leonardojpr_beetrack.database.ArticleDao;
import com.pruebatecnica.leonardojpr_beetrack.database.DaoMaster;
import com.pruebatecnica.leonardojpr_beetrack.database.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
/**
 * Created by Leonardojpr on 06/07/18.
 */
public class DBController {


    private static final String TAG = DBController.class.getSimpleName();
    public static final String DBNAME = "com.pruebatecnica.leonardojpr_beetrack.sqlite";


    private static DBController dbController = null;
    private DaoMaster daoMaster = null;

    public static DBController getControler() {
        if (dbController == null)
            dbController = new DBController();
        return dbController;
    }

    public void createDB() {
        DaoMaster.createAllTables(connection(), true);
    }

    @SuppressWarnings("unused")
    public void cleanBD() {
        DaoMaster daoMaster = new DaoMaster(connection());
        daoMaster.newSession().clear();
    }

    private Database connection() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(App.getAppContext(), DBNAME, null);
        Database db = openHelper.getWritableDb();
        return db;
    }

    private DaoSession getSession() {
        if (daoMaster == null)
            daoMaster = new DaoMaster(connection());
        return daoMaster.newSession();
    }

    public void createArticle(Article article) {
        ArticleDao articleDao = getSession().getArticleDao();
        articleDao.insertOrReplace(article);
    }

    public void removeArticle(Article article) {
        ArticleDao articleDao = getSession().getArticleDao();
        articleDao.delete(article);
    }

    public Article getArticle(Long id) {
        DaoSession session = getSession();
        ArticleDao articleDao = session.getArticleDao();
        return articleDao.load(id);
    }

    public List<Article> getArticleList() {
        DaoSession session = getSession();
        ArticleDao articleDao = session.getArticleDao();
        return articleDao.loadAll();
    }

    public boolean existArticle(String title) {
        DaoSession session = getSession();
        ArticleDao articleDao = session.getArticleDao();
        QueryBuilder<Article> qb = articleDao.queryBuilder();
        qb.where(ArticleDao.Properties.Title.eq(title));

        return qb.list().isEmpty();
    }

    public List<Article> getArticleFavoriteList() {
        DaoSession session = getSession();
        ArticleDao articleDao = session.getArticleDao();
        QueryBuilder<Article> qb = articleDao.queryBuilder();
        qb.where(ArticleDao.Properties.IsFavorite.eq(true));

        return qb.list();
    }
}
