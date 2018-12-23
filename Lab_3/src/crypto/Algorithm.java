package crypto;

interface Algorithm {
    String crypt(String in);
    String decrypt(String in);
}
