package fr.emse.pi.nextassist;

import fr.emse.pi.nextassist.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>
{
    List<Event> findByName(@Param("name") String name);
    Event findById(@Param("id") Long id);

}
