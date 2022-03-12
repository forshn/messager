package ru.forsh.sweater.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    //t means that the collection is not a collection of entities, but a collection of simple types (Strings, etc.) or a collection of embeddable elements (class annotated with @Embeddable).
    //It also means that the elements are completely owned by the containing entities: they're modified when the entity is modified, deleted when the entity is deleted, etc. They can't have their own lifecycle.
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) // FetchType - то, как будет подгружаться
    // таблица ролей вместе с User. Eager - сразу, при запросе юзера загружаются и роли, Lazy - только если к ним обратиться напрямую
    // Eager - когда в таблице мало данных, Lazy - когда много (например класс институт - у него много студентов
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    // Данное поле будет храниться в отдельной таблице, к которой нет маппинга
    //@JoinColumn - значит что таблица роли будет соединяться (референс) с User через user_id
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
