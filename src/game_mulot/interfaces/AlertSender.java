package game_mulot.interfaces;

/**
 * Interface pour les objets susceptibles d'envoyer une alertes � un
 * AlertReceiver.
 * 
 *
 */
public interface AlertSender {

	/**
	 * Envoie une alerte � son parent.
	 * 
	 * @param alert
	 *            - l'alerte envoy�e.
	 */
	public void sendAlert(Enum<?> alert);

	/**
	 * Envoie une alert soit � son parent soit � un autre objet ou les deux.
	 * 
	 * @param alert
	 *            - l'alerte envoy�e.
	 * @param receiver
	 *            - le reeveur de l'alerte, qui n'est pas forcement le parent.
	 */
	public void sendAlert(Enum<?> alert, AlertReceiver receiver);
}
