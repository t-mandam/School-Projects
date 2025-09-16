class Athlete:
    total_athletes = 0

    def __init__(self, name, age, country=None, salary=None):
        self.name = name
        self.age = int(age)
        self.country = country
        self.salary = int(float(salary)) if salary else None
        Athlete.total_athletes += 1

    def printStats(self):
        pass

    def printEndorsement(self):
        pass