package pojos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CatFactsResponse {

    Status status;
    String _id;
    String user;
    String text;
    int __v;
    String source;
    Date updatedAt;
    String type;
    Date createdAt;
    boolean deleted;
    boolean used;
}
