package fr.emse.pi.nextassist;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.omg.DynamicAny.DynValueCommonOperations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

public class EventTest  {

    protected static final DbSetupTracker TRACKER = new DbSetupTracker();

    public static final Operation DELETE_ALL = deleteAllFrom("Event", "Task");

    public static final Operation INSERT_REFERENCE_DATA =
            sequenceOf(
                    insertInto("event")
                            .withDefaultValue("movable", true)
                            .columns("name","start_date","location")
                            .values("Gala",(2018-02-10),"Centre des congrés")
                    .build());

    private DataSource dataSource;

    @Autowired
    private EventRepository eventRepository;

    @Before
    public void prepare() throws Exception {
        Operation operation =
                sequenceOf(
                        DELETE_ALL,
                        INSERT_REFERENCE_DATA,
                        insertInto("event")
                                .columns("name","location")
                                .values("Debut","Saint-Etienne")
                        .build());
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
    }

    @Test
    public void shouldFindByName() {
        TRACKER.skipNextLaunch();
        assertThat(eventRepository.findByName("Debut")).isNotEmpty();
        assertThat(eventRepository.findByName("Gala")).isNotEmpty();
        assertThat(eventRepository.findByName("Saint-Etienne")).isEmpty();
    }


}
