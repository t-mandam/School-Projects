from utils import load_file, save_file, delete_athlete, show_athlete_info, print_stats, display_chart

athletes = []
modified = False
filename = ""

def main_menu():
    global modified
    while True:
        print("\nMenu:")
        print("1. Load File")
        print("2. Print Stats")
        print("3. Delete Athlete")
        print("4. Save File")
        print("5. Athlete Info")
        print("6. Display Chart")
        print("7. Exit")

        choice = input("Enter choice: ").strip()
        # Load File
        ##############
        if choice == '1':
            filename = input("Filename to load: ").strip()
            loaded = load_file(filename, athletes)
            modified = modified or loaded
        # Print Stats
        ##############
        elif choice == '2':
            print_stats(athletes)
        # Delete Athlete
        ##############
        elif choice == '3':
            modified = delete_athlete(athletes) or modified
        # Save File
        ##############
        elif choice == '4':
            modified = not save_file(filename, athletes)
        # Athlete Info
        ##############
        elif choice == '5':
            show_athlete_info(athletes)
        # Display Chart
        ##############
        elif choice == '6':
            display_chart(athletes)
        # Exit
        elif choice == '7':
            if modified:
                confirm = input("Unsaved changes will be lost. Exit? (y/n): ").strip().lower()
                if confirm != 'y':
                    continue
            print("Goodbye!")
            break
        # Invalid Option
        ##############
        else:
            print("Invalid option.")

if __name__ == "__main__":
    main_menu()
