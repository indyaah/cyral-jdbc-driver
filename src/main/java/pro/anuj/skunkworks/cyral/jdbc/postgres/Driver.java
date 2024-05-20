package pro.anuj.skunkworks.cyral.jdbc.postgres;

import static pro.anuj.skunkworks.cyral.jdbc.common.TokenLoader.getAccessToken;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.postgresql.PGProperty;
import pro.anuj.skunkworks.cyral.jdbc.common.Token;

public class Driver extends org.postgresql.Driver {

  @Override
  public Connection connect(String url, Properties info) throws SQLException {
    try {
      final Token token = getAccessToken();
      PGProperty.PASSWORD.set(info, token.getAccessToken());
      return super.connect(url, info);
    } catch (Exception e) {
      throw new SQLException(e);
    }
  }
}
