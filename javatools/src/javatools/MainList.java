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

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

/**
 * Main Java Class to compute the "dependencies" section of the pom.xml file.
 */
public class MainList {
  private static String REPOSITORY_NAME = "neon_repository";
  private static File REPOSITORY_FOLDER = new File("../aggregator/" + REPOSITORY_NAME + "/final");

  public static void main(String[] args) throws Exception {
    List<File> pomFiles = searchPomFiles(REPOSITORY_FOLDER);

    List<MavenCoordinate> coodinates = new ArrayList<>();
    for (File pomFile : pomFiles) {
      String path = toPath(pomFile);
      if (path.startsWith("org/eclipse")) {
        coodinates.add(toMavenCoordinate(pomFile));
      }
    }

    Collections.sort(coodinates, new Comparator<MavenCoordinate>() {

      @Override
      public int compare(MavenCoordinate o1, MavenCoordinate o2) {
        int r;
        r = o1.getGroupId().compareTo(o2.getGroupId());
        if (r != 0) {
          return r;
        }
        r = o1.getArtifactId().compareTo(o2.getArtifactId());
        if (r != 0) {
          return r;
        }
        r = o1.getVersion().compareTo(o2.getVersion());
        return r;
      }
    });

    StringBuilder sb = new StringBuilder();
    for (MavenCoordinate c : coodinates) {
      sb.append("    <dependency>\n");
      sb.append("      ");
      sb.append("<groupId>");
      sb.append(c.getGroupId());
      sb.append("</groupId>\n");
      sb.append("      ");
      sb.append("<artifactId>");
      sb.append(c.getArtifactId());
      sb.append("</artifactId>\n");
      sb.append("      ");
      sb.append("<version>");
      sb.append(c.getVersion());
      sb.append("</version>\n");
      sb.append("    </dependency>\n");
    }
    System.out.println(sb.toString());
  }

  private static List<File> searchPomFiles(File input) {
    List<File> pomFiles = new ArrayList<>();
    if (!input.isDirectory()) {
      return pomFiles;
    }

    File[] children = input.listFiles(new FilenameFilter() {

      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".pom");
      }
    });
    for (File file : children) {
      pomFiles.add(file);
    }

    for (File file : input.listFiles()) {
      if (file.isDirectory()) {
        pomFiles.addAll(searchPomFiles(file));
      }
    }
    return pomFiles;
  }

  private static String toPath(File f) {
    return f.getAbsolutePath().substring(REPOSITORY_FOLDER.getAbsolutePath().length() + 1).replaceAll("\\\\", "/");
  }

  private static MavenCoordinate toMavenCoordinate(File pomFile) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(pomFile);

    XPathFactory xPathfactory = XPathFactory.newInstance();
    XPath xpath = xPathfactory.newXPath();
    XPathExpression groupIdExpr = xpath.compile("/project/groupId");
    XPathExpression artifactIdExpr = xpath.compile("/project/artifactId");
    XPathExpression versionExpr = xpath.compile("/project/version");

    String groupId = groupIdExpr.evaluate(doc);
    if (groupId == null || groupId.isEmpty()) {
      XPathExpression parentGroupIdExpr = xpath.compile("/project/groupId");
      groupId = parentGroupIdExpr.evaluate(doc);
      if (groupId == null || groupId.isEmpty()) {
        throw new IllegalStateException("groupId is not found");
      }
    }
    String artifactId = artifactIdExpr.evaluate(doc);
    if (artifactId == null || artifactId.isEmpty()) {
      throw new IllegalStateException("artifactId is not found");
    }
    String version = versionExpr.evaluate(doc);
    if (version == null || version.isEmpty()) {
      XPathExpression parentVersionExpr = xpath.compile("/project/parent/version");
      version = parentVersionExpr.evaluate(doc);
      if (version == null || version.isEmpty()) {
        throw new IllegalStateException("version is not found");
      }
    }

    return new MavenCoordinate(groupId, artifactId, version);
  }

}
