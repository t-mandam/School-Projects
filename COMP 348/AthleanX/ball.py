from athlete import Athlete

class BallPlayer(Athlete):
    total_ball_players = 0

    def __init__(self, name, age, team_name, jersey_number, country=None, salary=None, endorsement=None):
        super().__init__(name, age, country, salary)
        self.team_name = team_name
        self.jersey_number = int(jersey_number) if jersey_number else None
        self.endorsement = endorsement
        BallPlayer.total_ball_players += 1

    def printEndorsement(self):
        pass

class BasketballPlayer(BallPlayer):
    total_basketball = 0

    def __init__(self, name, age, team_name, jersey_number, country=None, salary=None, endorsement=None, three_point_pct=None, rebounds=None):
        super().__init__(name, age, team_name, jersey_number, country, salary, endorsement)
        self.three_point_pct = float(three_point_pct) if three_point_pct else None
        self.rebounds = int(rebounds) if rebounds else None
        BasketballPlayer.total_basketball += 1
        print(f"Basketball Player '{self.name}', {self.age} created; total #of basketball players {BasketballPlayer.total_basketball}.")

    def printStats(self):
        print(f"{self.name} - 3PT%: {self.three_point_pct * 100 if self.three_point_pct else None}%, Rebounds: {self.rebounds}")

    def printEndorsement(self):
        print(f"{self.name}'s endorsement: {self.endorsement}")
    
    def str(self):
        return f"BasketballPlayer: {self.name},{self.age},{self.team_name},{self.jersey_number},{self.country or ''},{self.salary or ''},{self.endorsement or ''},{self.three_point_pct if self.three_point_pct is not None else ''},{self.rebounds if self.rebounds is not None else ''}"

    @staticmethod
    def parse(text):
        parts = [p.strip() for p in text.split(',')]
        while len(parts) < 9: # Ensure there are at most 9 arguments
            parts.append(None)
        return BasketballPlayer(*parts[:9]) # Unpack the 9 elements into constructor

class FootballPlayer(BallPlayer):
    total_football = 0

    def __init__(self, name, age, team_name, jersey_number, country=None, salary=None, endorsement=None, touchdowns=None, passing_yards=None):
        super().__init__(name, age, team_name, jersey_number, country, salary, endorsement)
        self.touchdowns = int(touchdowns) if touchdowns else 0
        self.passing_yards = int(passing_yards) if passing_yards else 0
        FootballPlayer.total_football += 1
        print(f"Football Player '{self.name}', {self.age} created; total #of football players {FootballPlayer.total_football}.")

    def printStats(self):
        print(f"{self.name} - Touchdowns: {self.touchdowns}, Passing Yards: {self.passing_yards}")

    def printEndorsement(self):
        print(f"{self.name}'s endorsement: {self.endorsement}")
    
    def str(self):
        return f"FootballPlayer: {self.name},{self.age},{self.team_name},{self.jersey_number},{self.country or ''},{self.salary or ''},{self.endorsement or ''},{self.touchdowns},{self.passing_yards}"

    @staticmethod
    def parse(text):
        parts = [p.strip() for p in text.split(',')]
        while len(parts) < 9: # Ensure there are at most 9 arguments
            parts.append(None)
        return FootballPlayer(*parts[:9]) # Unpack the 9 elements into constructor
