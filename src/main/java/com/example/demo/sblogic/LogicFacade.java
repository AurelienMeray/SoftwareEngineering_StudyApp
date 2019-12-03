package com.example.demo.sblogic;

public class LogicFacade {

    //Instantiate with singleton method
    LogicController controller = LogicController.getInstance();

    /**
     * Default constructor
     */
    public LogicFacade() {

    }


    /**
     *
     */
    public void requestLoginPage() {
        // TODO implement here
        //controller.requestPage(LoginPage);
        //react frontend already handles pulling up loginpage?
    }

    /**
     *
     */
    public void displayRequest() {
        // TODO implement here
        // sequence diagram
    }

    /**
     *
     */
    public void verifyLoginRequest() {
        // TODO implement here
        controller.verifyLogin();
    }

    /**
     *
     */
    public void endSessionRequest() {
        // TODO implement here
        controller.endSessionRequest();
        //if request valid
        controller.endSession();
    }

    /**
     *
     */
    public void requestRegPage() {
        // TODO implement here
        //react handles page?
        controller.requestPage();
    }

    /**
     *
     */
    public void requestReg() {
        // TODO implement here
        controller.requestReg();
    }

    /**
     *
     */
    public void requestSearchPage() {
        // TODO implement here
        // react implements pages?
    }

    /**
     *
     */
    public void requestSearch() {
        // TODO implement here
        controller.requestSearch();

    }

    /**
     *
     */
    public void requestCreatePage() {
        // TODO implement here
        // look up sequence diagram
    }

    /**
     *
     */
    public void requestRoomCreate() {
        // TODO implement here
        controller.reqRoomCreate();
    }

    /**
     *
     */
    public void deleteRoomReq() {
        // TODO implement here
        controller.deleteRoomReq();
    }

    /**
     *
     */
    public void joinRoomReq() {
        // TODO implement here
        controller.joinRoomReq();
    }

    /**
     *
     */
    public void hidePass() {
        // TODO implement here
        controller.hashPass();
    }

}
