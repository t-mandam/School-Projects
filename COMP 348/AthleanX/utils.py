import matplotlib.pyplot as plt
from hockey import HockeyPlayer
from swimmer import Swimmer
from ball import BallPlayer, BasketballPlayer, FootballPlayer

# WRITE COMMENTS EXPLAINING THE FUNCTIONALITY OF EACH METHOD
# WRITE COMMENTS EXPLAINING THE FUNCTIONALITY OF EACH METHOD
# WRITE COMMENTS EXPLAINING THE FUNCTIONALITY OF EACH METHOD

def load_file(filename, athletes):
    sports_map = { # Map of sport types to their respective parsing methods and constructors
        'HockeyPlayer': HockeyPlayer.parse,
        'Swimmer': Swimmer.parse,
        'BasketballPlayer': BasketballPlayer.parse,
        'FootballPlayer': FootballPlayer.parse
    }
    try:
        with open(filename, 'r', encoding='utf-8') as f:
            lines = f.readlines() # Read all lines from the file
            for line in lines:
                if ':' not in line:
                    continue
                sport, data = line.split(':', 1) # Split line into type and data for arguments
                sport = sport.strip()
                data = data.strip()
                if sport in sports_map:
                    athlete = sports_map[sport](data) # Find Sport and Parse the data into the appropriate athlete constructor
                    athletes.append(athlete)
        return True
    except FileNotFoundError:
        print("File not found.")
        return False, 0

def save_file(filename, athletes):
    confirm = input(f"Are you sure you want to overwrite '{filename}'? (y/n): ").lower()
    if confirm != 'y':
        return False
    with open(filename, 'w', encoding='utf-8') as f:
        for athlete in athletes:
            f.write(athlete.str() + '\n') # Write each athlete's string representation to the file
    print("File saved.")
    return True

def delete_athlete(athletes):
    name = input("Enter the name of the athlete to delete: ")
    to_delete = [a for a in athletes if a.name == name] # Find athletes with the given exact name in Athletes list
    if not to_delete:
        print("No such athlete found.")
        return False
    if len(to_delete) > 1:
        confirm = input("Multiple athletes found. Delete all? (y/n): ").lower()
        if confirm != 'y':
            athletes.remove(to_delete[0])  # Remove only the first athlete if user does not confirm deletion of all
            # Decrement counters
            if isinstance(to_delete[0], HockeyPlayer):
                HockeyPlayer.total_hockey -= 1
            elif isinstance(to_delete[0], BasketballPlayer):
                BasketballPlayer.total_basketball -= 1
                BallPlayer.total_ball_players -= 1
            elif isinstance(to_delete[0], FootballPlayer):
                FootballPlayer.total_football -= 1
                BallPlayer.total_ball_players -= 1
            elif isinstance(to_delete[0], Swimmer):
                Swimmer.total_swimmers -= 1
            print(f"Deleted athlete.")
            return True
    for a in to_delete:
        athletes.remove(a) # Remove all athletes with the given name from the Athletes list
        # Decrement counters
        if isinstance(a, HockeyPlayer):
            HockeyPlayer.total_hockey -= 1
        elif isinstance(a, BasketballPlayer):
            BasketballPlayer.total_basketball -= 1
            BallPlayer.total_ball_players -= 1
        elif isinstance(a, FootballPlayer):
            FootballPlayer.total_football -= 1
            BallPlayer.total_ball_players -= 1
        elif isinstance(a, Swimmer):
            Swimmer.total_swimmers -= 1
    print(f"Deleted {len(to_delete)} athlete(s).")
    return True

def show_athlete_info(athletes):
    name = input("Enter athlete name: ")
    found = [a for a in athletes if a.name == name]
    if not found:
        print("Athlete not found.")
        return
    for a in found:
        a.printStats()
        a.printEndorsement()

def print_stats(athletes):
    print("\nStatistics:")
    print("-------------------")
    print(f"{len(athletes)} athletes")
    print(f"{HockeyPlayer.total_hockey} Hockey Players")
    print(f"{BallPlayer.total_ball_players} Ball Players ({BasketballPlayer.total_basketball} Basketball and {FootballPlayer.total_football} Football Players)")
    print(f"{Swimmer.total_swimmers} Swimmers")
    print("\nEndorsements:")
    print("-------------------")
    endorsements = [a.endorsement for a in athletes if hasattr(a, 'endorsement') and a.endorsement] # List of endorsements from athletes
    if not endorsements:
        print("None.")
    else :
        from collections import Counter
        counts = Counter(endorsements) # dictionary of endorsements (key) and their counts (value)
        for endorsement, count in sorted(counts.items()): # Sort endorsements (with counts) by name
            print(f"{endorsement} ({count})")
    print("\nGoals Scored:")
    print("-------------------")
    goals_athletes = [a for a in athletes if hasattr(a, 'goals_scored')]
    for a in sorted(goals_athletes, key=lambda x: (-x.goals_scored, x.name)): # sorts first by goals scored (- descending), then by name
        print(f"{a.goals_scored} {a.name}")
    print("\nTouchdowns:")
    print("-------------------")
    touchdowns_athletes = [a for a in athletes if hasattr(a, 'touchdowns')]
    for a in sorted(touchdowns_athletes, key=lambda x: (-x.touchdowns, x.name)):
        print(f"{a.touchdowns} {a.name}")

def display_chart(athletes):
    print("\nChart Options:")
    print("1. Number of Athletes (level 1)")
    print("2. Number of Athletes (leaf level)")
    print("3. Athletes Salaries (level 1)")
    print("4. Athletes Salaries (leaf level)")
    print("5. Endorsements")

    choice = input("Select a chart to display: ").strip()
    title = "Athlete Statistics"
    # Number of Athletes (Level 1)
    if choice == '1':
        title = "Number of Athletes per Sport (Level 1)"
        labels = ['HockeyPlayer', 'BallPlayer', 'Swimmer']
        values = [
            HockeyPlayer.total_hockey,
            BallPlayer.total_ball_players,
            Swimmer.total_swimmers
        ]
    # Number of Athletes (Leaf Level)
    elif choice == '2':
        title = "Number of Athletes per Sport (Leaf Level)"
        labels = ['HockeyPlayer', 'BasketballPlayer', 'FootballPlayer', 'Swimmer']
        values = [
            HockeyPlayer.total_hockey,
            BasketballPlayer.total_basketball,
            FootballPlayer.total_football,
            Swimmer.total_swimmers
        ]
    # Athletes Salaries (Level 1)
    elif choice == '3':
        title = "Average Salaries per Sport (Level 1)"
        class_salaries = {'HockeyPlayer': [], 'BallPlayer': [], 'Swimmer': []} # dictionary of average salaries
        for a in athletes:
            if isinstance(a, HockeyPlayer) and a.salary:
                class_salaries['HockeyPlayer'].append(a.salary)
            elif isinstance(a, (BasketballPlayer, FootballPlayer)) and a.salary:
                class_salaries['BallPlayer'].append(a.salary)
            elif isinstance(a, Swimmer) and a.salary:
                class_salaries['Swimmer'].append(a.salary)
        labels, values = [], []
        for sport, salaries in class_salaries.items(): # Iterate through the class_salaries dictionary
            if salaries:
                labels.append(sport) 
                values.append(sum(salaries) / len(salaries)) # Calculate average salary for each sport
    # Athletes Salaries (Leaf Level)
    elif choice == '4':
        title = "Average Salaries per Sport (Leaf Level)"
        class_salaries = {'HockeyPlayer': [], 'BasketballPlayer': [], 'FootballPlayer': [], 'Swimmer': []}
        for a in athletes:
            if isinstance(a, HockeyPlayer) and a.salary:
                class_salaries['HockeyPlayer'].append(a.salary)
            elif isinstance(a, BasketballPlayer) and a.salary:
                class_salaries['BasketballPlayer'].append(a.salary)
            elif isinstance(a, FootballPlayer) and a.salary:
                class_salaries['FootballPlayer'].append(a.salary)
            elif isinstance(a, Swimmer) and a.salary:
                class_salaries['Swimmer'].append(a.salary)
        labels, values = [], []
        for sport, salaries in class_salaries.items(): # Iterate through the class_salaries dictionary
            if salaries:
                labels.append(sport) 
                values.append(sum(salaries) / len(salaries)) # Calculate average salary for each sport
    # Endorsements
    elif choice == '5':
        title = "Endorsements Distribution"
        from collections import Counter
        endorsements = [a.endorsement for a in athletes if hasattr(a, 'endorsement') and a.endorsement]
        counts = Counter(endorsements)
        labels, values = list(counts.keys()), list(counts.values())
    # Invalid Choice
    else:
        print("Invalid choice.")
        return
    if not values or sum(values) == 0:
        print("No data available to display the chart.")
        return
    # Display Pie Chart
    plt.figure(figsize=(6, 6))
    plt.pie(values, labels=labels, autopct='%1.0f%%', startangle=90)
    plt.title(title)
    plt.axis('equal')
    plt.show()
