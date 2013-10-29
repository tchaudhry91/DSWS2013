package commands;

import network.Session;

import java.io.PrintStream;

public class Context {
    private Session session = null;
    private PrintStream outputStream = null;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public PrintStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }
}
