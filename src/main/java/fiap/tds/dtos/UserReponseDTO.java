package fiap.tds.dtos;




public class UserReponseDTO {
    private String username;
    private String position;

    public UserReponseDTO() {
    }

    public UserReponseDTO(String username, String position) {
        this.username = username;
        this.position = position;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
