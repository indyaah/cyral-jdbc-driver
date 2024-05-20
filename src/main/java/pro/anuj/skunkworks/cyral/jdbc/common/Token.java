package pro.anuj.skunkworks.cyral.jdbc.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

  private String accessToken;
  private Date createdAt;
  private Date validUntil;
}
