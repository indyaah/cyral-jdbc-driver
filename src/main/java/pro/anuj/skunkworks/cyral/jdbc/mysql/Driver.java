package pro.anuj.skunkworks.cyral.jdbc.mysql;

import static pro.anuj.skunkworks.cyral.jdbc.common.TokenLoader.getAccessToken;

import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.jdbc.NonRegisteringDriver;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import pro.anuj.skunkworks.cyral.jdbc.common.Token;

public class Driver extends NonRegisteringDriver {

  public Driver() throws SQLException {}

  @Override
  public Connection connect(String url, Properties info) throws SQLException {
    try {
      final Token token = getAccessToken();
      info.put(PropertyKey.PASSWORD.getKeyName(), token.getAccessToken());
      return super.connect(url, info);
    } catch (Exception e) {
      throw new SQLException(e);
    }
  }
}
