package com.store.malottaflavors.json;

import lombok.Data;

/**
 * Model for {@link ErrorApiResponse}.
 */
@Data
public class ErrorApiResponse {
  final private String error;
  final private String message;

  /**
   * Constructor for the {@link ErrorApiResponse}
   * 
   * @param error {@link String} what the error is.
   * @param message {@link String} message related to the error.
   */
  public ErrorApiResponse(final String error, final String message) {
    this.error = error;
    this.message = message;
  }
}
