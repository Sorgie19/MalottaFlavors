package com.store.malottaflavors.json;

import lombok.Data;

/**
 * Model for {@link MessageApiResponse}.
 */
@Data
public class MessageApiResponse {
  final private String message;

  /**
   * Constructor for the {@link MessageApiResponse}
   * 
   * @param message {@link String} message relating to the response
   */
  public MessageApiResponse(final String message) {
    this.message = message;
  }
}
