package com.mage.gerrit.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetRC {
    static final Pattern NETRC = Pattern.compile("(\\S+)");

    /**
     * 'default' netrc entry. This is the same as machine name except that
     * default matches any name. There can be only one default token, and it
     * must be after all machine tokens.
     */
    static final String DEFAULT_ENTRY = "default";

    /**
     * .netrc file entry
     */
    public static class NetRCEntry {
        /**
         * login netrc entry
         */
        public String login;

        /**
         * password netrc entry
         */
        public String password;

        /**
         * machine netrc entry
         */
        public String machine;

        /**
         * account netrc entry
         */
        public String account;

        /**
         * macdef netrc entry. Defines a macro. This token functions like the
         * ftp macdef command functions. A macro is defined with the specified
         * name; its contents begins with the next .netrc line and continues
         * until a null line (consecutive new-line characters) is encountered.
         * If a macro named init is defined, it is automatically executed as the
         * last step in the auto-login process.
         */
        public String macdef;

        /**
         * macro script body of macdef entry.
         */
        public String macbody;

        /**
         * Default constructor
         */
        public NetRCEntry() {
        }

        public boolean complete() {
            return login != null && password != null && machine != null;
        }
    }

    private File netrc;

    private long lastModified;

    private Map<String, NetRCEntry> hosts = new HashMap<>();

    private static final TreeMap<String, State> STATE = new TreeMap<String, NetRC.State>() {
        private static final long serialVersionUID = -4285910831814853334L;

        {
            put("machine", State.MACHINE);
            put("login", State.LOGIN);
            put("password", State.PASSWORD);
            put(DEFAULT_ENTRY, State.DEFAULT);
            put("account", State.ACCOUNT);
            put("macdef", State.MACDEF);
        }
    };

    enum State {
        COMMAND, MACHINE, LOGIN, PASSWORD, DEFAULT, ACCOUNT, MACDEF
    }

    /**
     * <p>Constructor for NetRC.</p>
     */
    public NetRC() {
        netrc = getDefaultFile();
        if (netrc != null)
            parse();
    }

    /**
     * <p>Constructor for NetRC.</p>
     *
     * @param netrc the .netrc file
     */
    public NetRC(File netrc) {
        this.netrc = netrc;
        parse();
    }

    private static File getDefaultFile() {
        File home = new File(System.getProperty("user.home"));
        File netrc = new File(home, ".netrc"); // on linux
        if (netrc.exists())
            return netrc;

        netrc = new File(home, "_netrc"); // on Windows
        if (netrc.exists())
            return netrc;

        return null;
    }

    /**
     * Get entry by host name
     *
     * @param host the host name
     * @return entry associated with host name or null
     */
    public NetRCEntry getEntry(String host) {
        if (netrc == null)
            return null;

        if (this.lastModified != this.netrc.lastModified())
            parse();

        NetRCEntry entry = this.hosts.get(host);

        if (entry == null)
            entry = this.hosts.get(DEFAULT_ENTRY);

        return entry;
    }

    /**
     * Get all entries collected from .netrc file
     *
     * @return all entries collected from .netrc file
     */
    public Collection<NetRCEntry> getEntries() {
        return hosts.values();
    }

    private void parse() {
        this.hosts.clear();
        this.lastModified = this.netrc.lastModified();

        try (BufferedReader r = new BufferedReader(new FileReader(netrc))) {
            String line;
            NetRCEntry entry = new NetRCEntry();
            State state;
            String macbody = "";
            Matcher matcher = NETRC.matcher("");
            while ((line = r.readLine()) != null) {
                // reading macbody
                if (entry.macdef != null && entry.macbody == null) {
                    if (line.length() == 0) {
                        entry.macbody = macbody;
                        macbody = "";
                        continue;
                    }
                    macbody += line + "\n"; ;
                    continue;
                }

                matcher.reset(line);
                while (matcher.find()) {
                    String command = matcher.group().toLowerCase(Locale.ROOT);
                    if (command.startsWith("#")) {
                        matcher.reset("");
                        continue;
                    }
                    state = STATE.get(command);
                    if (state == null)
                        state = State.COMMAND;

                    switch (state) {
                        case COMMAND:
                            break;
                        case ACCOUNT:
                            if (entry.account != null && entry.complete()) {
                                hosts.put(entry.machine, entry);
                                entry = new NetRCEntry();
                            }
                            if (matcher.find())
                                entry.account = matcher.group();
                            state = State.COMMAND;
                            break;
                        case LOGIN:
                            if (entry.login != null && entry.complete()) {
                                hosts.put(entry.machine, entry);
                                entry = new NetRCEntry();
                            }
                            if (matcher.find())
                                entry.login = matcher.group();
                            state = State.COMMAND;
                            break;
                        case PASSWORD:
                            if (entry.password != null && entry.complete()) {
                                hosts.put(entry.machine, entry);
                                entry = new NetRCEntry();
                            }
                            if (matcher.find())
                                entry.password = matcher.group();
                            state = State.COMMAND;
                            break;
                        case DEFAULT:
                            if (entry.machine != null && entry.complete()) {
                                hosts.put(entry.machine, entry);
                                entry = new NetRCEntry();
                            }
                            entry.machine = DEFAULT_ENTRY;
                            state = State.COMMAND;
                            break;
                        case MACDEF:
                            if (entry.macdef != null && entry.complete()) {
                                hosts.put(entry.machine, entry);
                                entry = new NetRCEntry();
                            }
                            if (matcher.find())
                                entry.macdef = matcher.group();
                            state = State.COMMAND;
                            break;
                        case MACHINE:
                            if (entry.machine != null && entry.complete()) {
                                hosts.put(entry.machine, entry);
                                entry = new NetRCEntry();
                            }
                            if (matcher.find())
                                entry.machine = matcher.group();
                            state = State.COMMAND;
                            break;
                    }
                }
            }

            // reading macbody on EOF
            if (entry.macdef != null && entry.macbody == null)
                entry.macbody = macbody;

            if (entry.complete())
                hosts.put(entry.machine, entry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
