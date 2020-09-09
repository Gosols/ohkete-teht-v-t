import urllib.request as r
import json

# fetch JSON data
url = r.urlopen("http://open-api.myhelsinki.fi/v1/events/")
html = url.read()
jsonObj = json.loads(html)
events = jsonObj["data"]


# algorithm found from the internets - Bubble Sort Algorithm
def bubbleSort(list):
    n = len(list)
    # Iterate through every event in list
    for i in range(n-1):
        # range(n) also work but outer loop will repeat one time more than needed.

        for j in range(0, n-i-1):
            # variables for cleaner code
            current_event = list[j]["event_dates"]["starting_day"]
            next_event = list[j+1]["event_dates"]["starting_day"]

            # if there's no date given to the current event
            if current_event is None:
                current_event = "no date"
                list[j]["event_dates"]["starting_day"] = "No Date"
            # if there's no date given to the next event
            if next_event is None:
                next_event = "no date"
                list[j+1]["event_dates"]["starting_day"] = "No Date"

            # if current (date) is bigger (later), put it below the next event
            if current_event > next_event:
                list[j], list[j+1] = list[j+1], list[j]


# sort events by date (and time)
bubbleSort(events)

current_date = ""
i = 0
# outer for loop for outputting dates
for event in events:

    # put the date in a variable (YYY-MM-DD)
    date = event["event_dates"]["starting_day"][0:10]
    if date != current_date:

        current_date = date
        print("")
        print(current_date)
        print("----------")
    # iterate through every event that has a matching date
    while i < len(events):
        name = events[i]["name"]["fi"]
        time = events[i]["event_dates"]["starting_day"][11:16]

        if name is None:
            name = "[unnamed]"
        elif time == "":
            time = "XX:XX"

        # temp variables for cleaner code
        output = time + ": " + name
        iteration = events[i]["event_dates"]["starting_day"][0:10]

        if iteration == current_date:
            print("     " + output)
            i = i + 1
        else:

            break
