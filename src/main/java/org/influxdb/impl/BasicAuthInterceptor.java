package org.influxdb.impl;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

  private String credentials;

  public BasicAuthInterceptor(final boolean basicAuth, final String user, final String password) {
    credentials = basicAuth ? Credentials.basic(user, password) : null;
  }

  @Override
  public Response intercept(final Chain chain) throws IOException {
    Request request = chain.request();

    Request.Builder builder = request.newBuilder();

    if(credentials != null) {
      builder.header("Authorization", credentials);
    }

    return chain.proceed(builder.build());
  }
}
