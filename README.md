# Yamb

1. `client.YambApp` - Glavna JavaFX aplikacija klasa

   - Inicijalizira korisničko sučelje
   - Upravlja prijavom igrača i povezivanjem sa serverom

2. `server.YambServer` - Server klasa

   - Upravlja povezivanjem klijenata
   - Koordinira komunikaciju između igrača

3. `client.YambClientThread` - Klijent klasa

   - Upravlja vezom sa serverom
   - Šalje i prima poruke

4. `model.Player` - Klasa za predstavljanje igrača

   - Sadrži informacije o igraču (ime, status, trenutni rezultat)

5. `model.Game` - Klasa za predstavljanje jedne partije Yamb-a

   - Upravlja tokom igre
   - Prati rezultate igrača

6. `model.Dice` - Klasa za predstavljanje kockica

   - Metode za bacanje i čuvanje kockica

7. `model.ScoreCard` - Klasa za praćenje rezultata

   - Sadrži kategorije i bodovanje

8. `util.Timer` - Klasa za upravljanje vremenom poteza

   - Odbrojava vrijeme za potez

9. `controller.GameController` - Kontroler klasa za upravljanje logikom igre

   - Povezuje model (model.Game, model.Player, model.ScoreCard) s prikazom

10. `controller.LobbyController` - Kontroler za upravljanje lobijem

    - Prikazuje listu igrača
    - Upravlja zahtjevima za igru

11. `view.GameView` - JavaFX view klasa za prikaz igre

    - Prikazuje stanje igre, kockice, rezultate

12. `view.LobbyView` - JavaFX view klasa za prikaz lobija

    - Prikazuje listu dostupnih igrača

13. `model.Message` - Klasa za enkapsulaciju poruka između klijenta i servera

    - Definira različite tipove poruka (zahtjev za igru, potez, itd.)

14. `client.NetworkHandler` - Klasa za upravljanje mrežnom komunikacijom

    - Enkapsulira slanje i primanje poruka preko socketa

15. `util.GameLogic` - Klasa koja sadrži pravila igre i logiku bodovanja
    - Implementira pravila Yamb-a
