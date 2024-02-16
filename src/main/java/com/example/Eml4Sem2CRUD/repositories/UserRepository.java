package com.example.Eml4Sem2CRUD.repositories;

import com.example.Eml4Sem2CRUD.config.DbRequests;
import com.example.Eml4Sem2CRUD.models.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    @Autowired
    private final JdbcTemplate jdbc;
    @Autowired
    private final DbRequests dbRequests;

    public List<User> findAll() {
        /**
         * Этот Java код создает объект RowMapper, который используется для сопоставления данных из строки результата базы данных (ResultSet) со свойствами объекта User.
         *
         * Внутри лямбда-выражения RowMapper, для каждой строки результата базы данных, создается новый объект User. Затем значения из столбцов "id", "firstName" и "lastName" в ResultSet присваиваются соответствующим свойствам объекта User. Наконец, созданный объект User возвращается в качестве результата метода mapRow из интерфейса RowMapper.
         *
         * Таким образом, этот код определяет, каким образом данные из строки результата базы данных должны быть преобразованы в объект User
         */
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(dbRequests.getFindAll(), userRowMapper);
    }

    public User save(User user) {
        jdbc.update(dbRequests.getSave(), user.getFirstName(), user.getLastName());
        return  user;
    }

    public void deleteById(int id) {
        jdbc.update(dbRequests.getDelete(), id);
    }

    public User getOne(int id) {
        return (User) jdbc.queryForObject(
                dbRequests.getGet(),
                new Object[]{id},
                new BeanPropertyRowMapper(User.class));
    }

    public User update(User user) {
        jdbc.update(dbRequests.getUpdate(), user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }
}
