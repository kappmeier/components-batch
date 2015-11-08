package org.zetool.components.batch.input;

//
//package de.tu_berlin.math.coga.batch.input;
//
//import de.tu_berlin.math.coga.batch.input.reader.InputFileReader;
//import de.tu_berlin.math.coga.batch.input.reader.UnknownFileReader;
//import java.io.File;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//
///**
// *
// * @author Martin Gross
// */
//public enum FileFormatEnum {
//
//    UNKNOWN(ProblemType.UNSPECIFIED, UnknownFileReader.class, "Unknown File Format");
////    DIMACS_MAXIMUM_FLOW(ProblemType.MAXIMUM_FLOW, DimacsMaximumFlowFileReader.class, "DIMACS Maximum Flow Problem", "max"),
////    DIMACS_MINIMUM_COST_FLOW(ProblemType.MINIMUM_COST_FLOW, DimacsMinimumCostFlowFileReader.class, "DIMACS Minimum Cost Flow Problem", "min", "net"),
////    RMFGEN_MAXIMUM_FLOW(ProblemType.MAXIMUM_FLOW, RMFGENMaximumFlowFileReader.class, "RMFGEN Maximum Flow Problem", "rmf"),
////    ZET_PROJECT(ProblemType.EVACUATION_PROJECT, ZETProjectFileReader.class, "ZET Evacuation Project", new ImageIcon("./icons/zet_24.png"), "zet");
//    private final String description;
//    private final String[] extensions;
//    private final Icon icon;
//    private final ProblemType problemType;
//    private final Class<? extends InputFileReader> reader;
//
//    private FileFormatEnum(ProblemType problemType, Class<? extends InputFileReader> reader, String description, String... extensions) {
//        this(problemType, reader, description, new ImageIcon("./icons/document_24.png"), extensions);        
//    }
//
//    private FileFormatEnum(ProblemType problemType, Class<? extends InputFileReader> reader, String description, Icon icon, String... extensions) {
//        this.description = description;
//        this.extensions = extensions;
//        this.icon = icon;
//        this.problemType = problemType;
//        this.reader = reader;
//    }    
//    
//    public String getDescription() {
//        return description;
//    }
//
//    public String[] getExtensions() {
//        return extensions;
//    }
//
//    public Icon getIcon() {
//        return icon;
//    }    
//    
//    public ProblemType getProblemType() {
//        return problemType;
//    }
//
//    public Class<? extends InputFileReader> getReader() {
//        return reader;
//    }
//    
//    public static FileFormatEnum determineFileFormat(File file) {
//        int index = file.getName().lastIndexOf(".");
//        String ext = (index >= 0)? file.getName().substring(file.getName().lastIndexOf(".") + 1) : "";
//        for (FileFormatEnum format : FileFormatEnum.values()) {
//            for (String extension : format.getExtensions()) {
//                if (ext.equals(extension)) {
//                    return format;
//                }
//            }
//        }
//        return UNKNOWN;
//    }
//    
//    private static List<String> extensionList;
//    
//    public static List<String> getAllKnownExtensions() {
//        if (extensionList == null) {
//            extensionList = new LinkedList<>();
//            for (int i = 1; i < FileFormatEnum.values().length; i++) {
//                extensionList.addAll(Arrays.asList(FileFormatEnum.values()[i].getExtensions()));
//            }
//        }
//        return extensionList;
//    }
//}
