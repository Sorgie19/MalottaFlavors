package com.store.malottaflavors.json;

import lombok.Data;

/**
 * Model for {@link UserApiResponse}.
 */
@Data
public class UserApiResponse<T> {
  final private Object users;

  /**
   * Parameterized {@link UserApiResponse} constructor
   * 
   * @param users {@link Iterable} or {link @Optional} list of users
   */
  public UserApiResponse(final Object users) {
    this.users = users;
  }
}
