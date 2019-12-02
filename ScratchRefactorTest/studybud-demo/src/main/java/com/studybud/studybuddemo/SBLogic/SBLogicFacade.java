package com.studybud.studybuddemo.SBLogic;

public class SBLogicFacade  {

    //Instantiate with singleton method
    SBLogicController controller = SBLogicController.getInstance();

    /**
     * Default constructor
     */
    public SBLogicFacade() {

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
    }

    /**
     *
     */
    public void verifyLoginRequest() {
        // TODO implement here
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
    }

    /**
     *
     */
    public void requestReg() {
        // TODO implement here
    }

    /**
     *
     */
    public void requestSearchPage() {
        // TODO implement here
    }

    /**
     *
     */
    public void requestSearch() {
        // TODO implement here
    }

    /**
     *
     */
    public void requestCreatePage() {
        // TODO implement here
    }

    /**
     *
     */
    public void requestRoomCreate() {
        // TODO implement here
    }

    /**
     *
     */
    public void deleteRoomReq() {
        // TODO implement here
    }

    /**
     *
     */
    public void joinRoomReq() {
        // TODO implement here
    }

    /**
     *
     */
    public void hidePass() {
        // TODO implement here
    }

}
