package Controllers;

import Model.ChangeOrPayModel;
import Model.IModel;
import Model.Message;
import View.AView;
import View.ChangeOrPayViewController;

public class ChangeOrPayController extends AController {
    private ChangeOrPayModel changeOrPayModel;
    private ChangeOrPayViewController changeOrPayViewController;

    public ChangeOrPayController(IModel model, AView view){
        changeOrPayModel=(ChangeOrPayModel)model;
        changeOrPayViewController=(ChangeOrPayViewController)view;
    }


    public void PayCash(Message message) {
        changeOrPayModel.payCash(message);
    }

    public void ChangeVacation(Message message) {
        changeOrPayModel.changeVacation(message);
    }
}
