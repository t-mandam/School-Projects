from athlete import Athlete

class Swimmer(Athlete):
    total_swimmers = 0

    def __init__(self, name, age, stroke_style, country=None, salary=None, personal_best_time=None):
        super().__init__(name, age, country, salary)
        self.stroke_style = stroke_style
        self.personal_best_time = float(personal_best_time) if personal_best_time else None
        Swimmer.total_swimmers += 1
        print(f"Swimmer '{self.name}', {self.age} created; total #of swimmers {Swimmer.total_swimmers}.")

    def printStats(self):
        print(f"{self.name} - Stroke: {self.stroke_style}, Best time: {self.personal_best_time}s")

    def str(self):
        return f"Swimmer: {self.name},{self.age},{self.stroke_style or ''},{self.country or ''},{self.salary or ''},{self.personal_best_time or ''}"

    @staticmethod
    def parse(text):
        parts = [p.strip() for p in text.split(',')]
        while len(parts) < 6: # Ensure we have at most 6 arguments
            parts.append(None)
        return Swimmer(*parts[:6]) # Unpack the 6 elements into constructor
