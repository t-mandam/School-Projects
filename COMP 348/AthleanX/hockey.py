from athlete import Athlete
from enum import Enum

class HockeyPosition(Enum):
    # Collection of hockey positions
    Forward = "Forward"
    Defenseman = "Defenseman"
    Goalie = "Goalie"

class HockeyPlayer(Athlete):
    total_hockey = 0

    def __init__(self, name, age, country=None, salary=None, position=None, goals_scored=0, stick_brand=None, skates_size=0):
        super().__init__(name, age, country, salary)
        self.position = HockeyPosition[position] if position in HockeyPosition.__members__ else None
        self.goals_scored = int(goals_scored) if goals_scored else 0
        self.stick_brand = stick_brand
        self.skates_size = int(skates_size) if skates_size else 0
        HockeyPlayer.total_hockey += 1
        print(f"Hockey Player '{self.name}', {self.age} created; total #of hockey players {HockeyPlayer.total_hockey}.")

    def printStats(self):
        print(f"{self.name} - Position: {self.position}, Goals: {self.goals_scored}")

    def str(self):
        return f"HockeyPlayer: {self.name},{self.age},{self.country or ''},{self.salary or ''},{self.position.name if self.position else ''},{self.goals_scored},{self.stick_brand or ''},{self.skates_size}"

    @staticmethod
    def parse(text):
        parts = [p.strip() for p in text.split(',')]
        while len(parts) < 8: # Ensure there are at most 8 arguments
            parts.append(None)
        return HockeyPlayer(*parts[:8]) # Unpack the 8 elements into constructor