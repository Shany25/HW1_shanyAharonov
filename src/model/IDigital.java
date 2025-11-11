package model;

/**
 * Interface representing digital communication methods.
 * Classes implementing this interface must have a method that returns the digital communication.
 */
public interface IDigital {
    /**
     * Prints the communication method for a digital message.
     * @return A string describing the communication method.
     */
    public String printCommunicationMethod();
}

