
package fiap.tds.dtos;

public class TypeEventDTO {
    private String type;
    private String position;

    public TypeEventDTO() {
    }

    public TypeEventDTO(String type, String position) {
        this.type = type;
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}



