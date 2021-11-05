package ua.training.model.service;

import org.mindrot.jbcrypt.BCrypt;

import java.util.function.Function;

/**
 * The BCrypt service.
 */
public class BCryptService {

    private final int logRounds;

    /**
     * Instantiates a new BCrypt service.
     *
     * @param logRounds the log rounds
     */
    public BCryptService(int logRounds) {
        this.logRounds = logRounds;
    }

    /**
     * Hash string.
     *
     * @param password the password
     * @return the string
     */
    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    /**
     * Verify hash.
     *
     * @param password the password
     * @param hash     the hash
     * @return the boolean
     */
    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    /**
     * Verify and update hash.
     *
     * @param password   the password
     * @param hash       the hash
     * @param updateFunc the update func
     * @return the boolean
     */
    public boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
        if (BCrypt.checkpw(password, hash)) {
            int rounds = getRounds(hash);
            // It might be smart to only allow increasing the rounds.
            // If someone makes a mistake the ability to undo it would be nice though.
            if (rounds != logRounds) {
                String newHash = hash(password);
                return updateFunc.apply(newHash);
            }
            return true;
        }
        return false;
    }

    /*
     * Gets the number of encoding rounds.
     *
     * We only care about rounds currently among the other BCrypt internals.
     *
     */
    private int getRounds(String salt) {
        char minor = (char)0;
        int off = 0;

        if (salt.charAt(0) != '$' || salt.charAt(1) != '2')
            throw new IllegalArgumentException ("Invalid salt version");
        if (salt.charAt(2) == '$')
            off = 3;
        else {
            minor = salt.charAt(2);
            if (minor != 'a' || salt.charAt(3) != '$')
                throw new IllegalArgumentException ("Invalid salt revision");
            off = 4;
        }

        // Extract number of rounds
        if (salt.charAt(off + 2) > '$')
            throw new IllegalArgumentException ("Missing salt rounds");
        return Integer.parseInt(salt.substring(off, off + 2));
    }
}