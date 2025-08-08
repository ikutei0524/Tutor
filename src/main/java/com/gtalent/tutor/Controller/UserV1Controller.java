package com.gtalent.tutor.Controller;
import java.util.List;
import com.gtalent.tutor.models.User;
import com.gtalent.tutor.models.request.CreateUserRequest;
import com.gtalent.tutor.models.request.UpdateUserRequest;
import com.gtalent.tutor.responses.CreateUserResponse;
import com.gtalent.tutor.responses.GetUserResponse;
import com.gtalent.tutor.responses.UpdateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/uAsers")
@CrossOrigin("*")
//由建構子注入
//這樣寫會更詳細,甚至我還可以加入一些邏輯
public class UserV1Controller {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserV1Controller(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    //由annotation注入
    //也可以這樣寫
    //public class UserV1Controller{
    //@Autowired
    //private JdbcTemplate jdbcTemplate;}

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {

        String sql = "insert into users(username,email) values (?, ?)";
        int rowsAffected = jdbcTemplate.update(sql,request.getUsername(),request.getEmail());
        if(rowsAffected>0){
            CreateUserResponse response = new CreateUserResponse(request.getUsername());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<GetUserResponse>>getAllUsers(){
        String sql ="select * from users";
        List<User>users = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
        return ResponseEntity.ok(users.stream().map(GetUserResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse>getUserById(@PathVariable int id){
        String sql = "SELECT * FROM users WHERE id = ?";
        //利用try-catch包住,避免找不到資料時出現 500 錯誤
        try{
            User user = jdbcTemplate.queryForObject
                    (sql,new BeanPropertyRowMapper<>(User.class),id);
        return ResponseEntity.ok(new GetUserResponse(user));
        //找到時,回傳id
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
            //找不到時,回傳http,404,notfound給使用者
            //<return ResponseEntity.ok(new GetUserResponse(user));>
            //上面這一行可以直接查詢id,如果該id在資料庫找不到，queryForObject
            // ,就會拋出例外 EmptyResultDataAccessException
            // ,沒有處理就會出現 500 Internal Server Error。
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse>updateUserById
            (@PathVariable int id, @RequestBody UpdateUserRequest request){
        String sql = "Update users set username = ? where id = ?";
        int rowsAffected = jdbcTemplate.update(sql,request.getUsername(),id);
        if(rowsAffected>0){
            UpdateUserResponse response = new UpdateUserResponse(request.getUsername());
            return ResponseEntity.ok(response);
    }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User>deleteUserByid(@PathVariable int id){
        String sql = "Delete FROM users WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected > 0 ) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/search")
    //1.直接將keyword直接綁進去
    public ResponseEntity<?> searchUsers(@RequestParam String keyword) {
        try {
            String sql = "SELECT * FROM users WHERE username LIKE ?";
            List<User> users = jdbcTemplate.query(
                    sql,
                    new Object[]{"%" + keyword + "%"},
                    new BeanPropertyRowMapper<>(User.class)
            );
            return ResponseEntity.ok
                    (users.stream()
                            .map(GetUserResponse::new)
                            .toList());
        } catch (Exception e)
        {e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}

