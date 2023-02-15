package com.example.asm_networking.ASM.Constant.Respone;

public class RegisterResponse {

    private Boolean status;
    private Boolean result;

    public RegisterResponse() {
    }

    public RegisterResponse(Boolean status, Boolean result) {
        this.status = status;
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
