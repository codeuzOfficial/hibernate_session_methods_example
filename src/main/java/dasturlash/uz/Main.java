package dasturlash.uz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        saveData();
//        getIdentifier();
//        createQuery();
//        delete();
//        get();
//        refresh();
//        save();
//        saveAsUpdate();
//        update();
//        saveOrUpdate();
//        merge();
//        persist();
//        load();
        contains();
    }

    public static void getIdentifier() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        StudentEntity student = session.get(StudentEntity.class, 1);
        student.setId(null);

        Integer studentId = (Integer) session.getIdentifier(student);
        System.out.println(studentId);
        session.close();
        factory.close();
    }

    public static void createQuery() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query<StudentEntity> query = session.createQuery("from StudentEntity where name like '%Ali%'");
        List<StudentEntity> list = query.list();
        for (StudentEntity entity : list) {
            System.out.println(entity);
        }

        session.close();
        factory.close();
    }

    public static void delete() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Transaction t = session.beginTransaction();

        StudentEntity student = session.get(StudentEntity.class, 2); // get student
//        StudentEntity student = new StudentEntity();
//        student.setId(1);

        session.delete(student); // delete student

        t.commit();
        session.close();
        factory.close();
    }

    public static void get() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        StudentEntity student = session.get(StudentEntity.class, 3); // get student
        System.out.println(student);

        session.close();
        factory.close();
    }

    public static void refresh() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        StudentEntity student = session.get(StudentEntity.class, 3); // get student

        Transaction transaction = session.beginTransaction();

        transaction.commit();

        session.close();
        factory.close();
    }

    public static void save() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        // students
        StudentEntity student = new StudentEntity();
        student.setName("Ali");
        student.setSurname("Aliyev");
        student.setCreatedDate(LocalDateTime.now());

        session.save(student);

        t.commit();
        session.close();
        factory.close();
    }

    public static void saveAsUpdate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        StudentEntity student = session.get(StudentEntity.class, 10); // get student

        student.setName("Alishbek");
        session.save(student);

        t.commit();
        session.close();
        factory.close();
    }

    public static void update() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        StudentEntity student = session.get(StudentEntity.class, 3); // get student

        student.setName("Mazgibek");
        session.update(student);

        t.commit();
        session.close();
        factory.close();
    }

    public static void saveOrUpdate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentEntity student = new StudentEntity();
        student.setName("Toshmat");
        student.setSurname("Toshmatov");
        student.setCreatedDate(LocalDateTime.now());

        session.saveOrUpdate(student);

        t.commit();
        session.close();
        factory.close();
    }

    public static void merge() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentEntity student = session.get(StudentEntity.class, 4); // get student
        System.out.println(student);
        //  session.evict(student);

        student.setName("Mazgibek5");
        student.setSurname("Lattayev5");

        StudentEntity otherObj = (StudentEntity) session.merge(student);
        // otherObj != student bular bir biriga teng emas.
        t.commit();
        System.out.println(otherObj);

        session.close();
        factory.close();
    }

    public static void persist() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentEntity student = new StudentEntity();
        student.setName("Vali11");
        student.setSurname("Valiyev11");
        student.setCreatedDate(LocalDateTime.now());

        session.persist(student);

        t.commit();

        session.close();
        factory.close();
    }

    public static void load() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
//        Transaction t = session.beginTransaction();

//        StudentEntity student = session.get(StudentEntity.class, 4); // return fake object
        StudentEntity student = session.load(StudentEntity.class, 4); // return fake object
        System.out.println("fitfilionta ish");
        System.out.println(student); // will select real object
//        t.commit();

        session.close();
        factory.close();
    }

    public static void contains() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        StudentEntity student = session.get(StudentEntity.class, 4);
        session.evict(student);
        System.out.println(session.contains(student));

        session.close();
        factory.close();
    }

    public static void saveData() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        // students
        StudentEntity student1 = new StudentEntity();
        student1.setName("Ali");
        student1.setSurname("Aliyev");
        student1.setCreatedDate(LocalDateTime.now());
        session.save(student1);

        StudentEntity student2 = new StudentEntity();
        student2.setName("Valish");
        student2.setSurname("Valiyev");
        student2.setCreatedDate(LocalDateTime.now());
        session.save(student2);

        StudentEntity student3 = new StudentEntity();
        student3.setName("Toshmat");
        student3.setSurname("Toshmatov");
        student3.setCreatedDate(LocalDateTime.now());
        session.save(student3);

        StudentEntity student4 = new StudentEntity();
        student4.setName("Eshmat");
        student4.setSurname("Eshmat");
        student4.setCreatedDate(LocalDateTime.now());
        session.save(student4);

        t.commit();

        factory.close();
        session.close();
    }
}