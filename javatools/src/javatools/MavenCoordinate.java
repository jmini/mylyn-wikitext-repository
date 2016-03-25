/*******************************************************************************
 * Copyright (c) 2016 Jeremie Bresson.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     Jeremie Bresson - initial API and implementation
 ******************************************************************************/
package javatools;

public class MavenCoordinate {
  private String groupId;
  private String artifactId;
  private String version;

  public MavenCoordinate(String groupId, String artifactId, String version) {
    super();
    this.groupId = groupId;
    this.artifactId = artifactId;
    this.version = version;
  }

  public String getGroupId() {
    return groupId;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public String getVersion() {
    return version;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((artifactId == null) ? 0 : artifactId.hashCode());
    result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
    result = prime * result + ((version == null) ? 0 : version.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    MavenCoordinate other = (MavenCoordinate) obj;
    if (artifactId == null) {
      if (other.artifactId != null) return false;
    }
    else if (!artifactId.equals(other.artifactId)) return false;
    if (groupId == null) {
      if (other.groupId != null) return false;
    }
    else if (!groupId.equals(other.groupId)) return false;
    if (version == null) {
      if (other.version != null) return false;
    }
    else if (!version.equals(other.version)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "MavenCoordinate [groupId=" + groupId + ", artifactId=" + artifactId + ", version=" + version + "]";
  }
}
