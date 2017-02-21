package objis.com.database.Model;

/**
 * Created by yeo-sglo on 25/12/16.
 */

public class MyDBSchema {

    public static final class EtudiantTable{
        public static final String NAME = "etudiants";

        public static final class MyColumns{
            public static final String id ="id";
            public static final String nom ="nom";
            public static final String prenom ="prenom";
            public static final String dateNaiss ="dateNaiss";
            public static final String lieuNaiss ="lieuNaiss";
            public static final String filiere ="filiere";
        }
    }

    public static final class ProfTable{
        public static final String NAME = "proffesseurs";

        public static final class MyColumns{
            public static final String id ="id";
            public static final String nom ="nom";
            public static final String prenom ="prenom";
            public static final String matiere ="matiere";
            public static final String diplome ="diplome";
        }
    }
}
