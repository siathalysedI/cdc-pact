package com.producer.creditcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GitBranchName {

    public static String getCurrentGitBranch() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec( "git name-rev --name-only HEAD" );
        process.waitFor();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader( process.getInputStream() ) );

        return reader.readLine();
    }

    public static String getCurrentGitCommit() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec( "git rev-parse HEAD" );
        process.waitFor();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader( process.getInputStream() ) );

        return reader.readLine();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(getCurrentGitBranch());
        System.out.println(getCurrentGitCommit());
    }
}
