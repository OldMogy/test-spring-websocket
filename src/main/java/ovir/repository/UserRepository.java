package ovir.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import ovir.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    @PreAuthorize("(#user.id == null) or (#user.id == authentication.principal)")
    <S extends User> S save(@Param("user") S user);

    @Override
    @RestResource(exported = false)
    <S extends User> Iterable<S> save(Iterable<S> idList);

    @Override
    User findOne(Long id);

    @Override
    boolean exists(Long id);

    @Override
    Iterable<User> findAll();

    @Override
    Iterable<User> findAll(Iterable<Long> id);

    @Override
    long count();

    @Override
    @PreAuthorize("authentication.principal == #id")
    void delete(Long id);

    @Override
    @PreAuthorize("authentication.principal == #user.id")
    void delete(User user);

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends User> user);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    User findByLogin(String login);
}
