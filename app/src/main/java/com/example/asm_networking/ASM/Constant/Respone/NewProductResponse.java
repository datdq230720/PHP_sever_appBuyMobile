package com.example.asm_networking.ASM.Constant.Respone;

public class NewProductResponse {

    private Boolean result, status;

    public NewProductResponse() {
    }

    public NewProductResponse(Boolean result, Boolean status) {
        this.result = result;
        this.status = status;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
