package sample.net.avazu.www.gpdemo;

/**
 * Created by csc on 15/8/25.
 */
public interface AdSubscriptionListener {
    public void onAdDismiss();

    public void onAdLoadFailed();

    public void onAdLoadSuccess();
}
