// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.model.data;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import java.time.Instant;
import java.util.UUID;

/** Class representing a registered user. */
public class User {
  private final UUID id;
  private final String name;
  private String status;
  private String blobKey;
  private final String passwordHash;
  private final Instant creation;
  private String phoneNumber;
  private boolean wantsNotifications;

  /**
   * Constructs a new User.
   *
   * @param id the ID of this User
   * @param name the username of this User
    * @param status the status of this User
   * @param passwordHash the password hash of this User
   * @param creation the creation time of this User
   * @param blobKey the BlobKeyof this User
   * @param phoneNumber the phoneNumber of this User
   */
 /* * Constructs a new User.
  *
  * @param id the ID of this User
  * @param name the username of this User
  * @param status the status of this User
  * @param passwordHash the password hash of this User
  * @param creation the creation time of this User
  * @param blobKey the BlobKeyof this User
  * @param phoneNumber the phoneNumber of this User
  */

  public User(UUID id, String name, String passwordHash, Instant creation) {
    this.id = id;
    this.name = name;
    this.passwordHash = passwordHash;
    this.creation = creation;
    wantsNotifications = false;
  }

  /** Returns the ID of this User. */
  public UUID getId() {
    return id;
  }
  /** Returns the BlobKey of this User. */
  public String getBlobKey() {
    return blobKey;
  }

  /** sets the BlobKey of this User. */
  public void setStatus(String value) {
    status = value;
  }

  /** Returns the BlobKey of this User. */
  public String getStatus() {
    return status;
  }

  /** sets the BlobKey of this User. */
  public void setBlobKey(String key) {
    blobKey = key;
  }

  /** Returns the username of this User. */
  public String getName() {
    return name;
  }

  /** Returns the password hash of this User. */
  public String getPasswordHash() {
    return passwordHash;
  }

  /** Returns the creation time of this User. */
  public Instant getCreationTime() {
    return creation;
  }

  /** Returns the phoneNumber of this User. */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /** Sets the phoneNumber of this User. */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public boolean wantsNotifications() {
	  return wantsNotifications;
  }
  
  public void setNotifications(boolean wantsNotifications) {
	  this.wantsNotifications = wantsNotifications;
  }
}
