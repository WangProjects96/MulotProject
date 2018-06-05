package game_mulot.interfaces;

/**
 * Interface pour les objets susceptibles d'envoyer une alertes à un
 * AlertReceiver.
 * 
 *
 */
public interface AlertSender {

	/**
	 * Envoie une alerte à son parent.
	 * 
	 * @param alert
	 *            - l'alerte envoyée.
	 */
	public void sendAlert(Enum<?> alert);

	/**
	 * Envoie une alert soit à son parent soit à un autre objet ou les deux.
	 * 
	 * @param alert
	 *            - l'alerte envoyée.
	 * @param receiver
	 *            - le reeveur de l'alerte, qui n'est pas forcement le parent.
	 */
	public void sendAlert(Enum<?> alert, AlertReceiver receiver);
}
