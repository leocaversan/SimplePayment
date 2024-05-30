package br.com.simple_payment.authorization;

public record Authorization(
        String message,
        Data data ) {
    public boolean isAuthorized(){
        return data.getAuthorization();
    }
}

class Data{
    private boolean authorization;

    public boolean getAuthorization(){
        return authorization;
    }
    public void setAuthorization(boolean authorization){
        this.authorization = authorization;
    }
 }