package fr.emse.pi.nextassist;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

public class TaskTest {

    public static final Operation DELETE_ALL = deleteAllFrom("Event", "Task");

    public static final Operation INSERT_REFERENCE_DATA =
            sequenceOf(
                    insertInto("task")
                            .columns("name", "start_date", "priority")
                            .values("Gala", (2018 - 02 - 10), 5)
                            .build());

    private DataSource dataSource;

    @Before
    public void prepare() throws Exception {
        Operation operation =
                sequenceOf(
                        DELETE_ALL,
                        INSERT_REFERENCE_DATA,
                        insertInto("task")
                                .columns("name", "location")
                                .values("Debut", "Saint-Etienne")
                                .build());
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
    }
}