import urllib.request as r
import json

url = r.urlopen("http://open-api.myhelsinki.fi/v1/events/")
html = url.read()
jsonObj = json.loads(html)
events = jsonObj["data"]


# algorithm found from the internets
def bubbleSort(arr):
    n = len(arr)
    # Traverse through all array elements
    for i in range(n-1):
        # range(n) also work but outer loop will repeat one time more than needed.

        # Last i elements are already in place
        for j in range(0, n-i-1):
            current_event = arr[j]["event_dates"]["starting_day"]
            next_event = arr[j+1]["event_dates"]["starting_day"]

            if current_event is None:
                current_event = "no date"
                arr[j]["event_dates"]["starting_day"] = "No Date"
            if next_event is None:
                next_event = "no date"
                arr[j+1]["event_dates"]["starting_day"] = "No Date"

            if current_event > next_event:
                arr[j], arr[j+1] = arr[j+1], arr[j]


# sort events by date (and time)
bubbleSort(events)

current_date = ""
i = 0
for event in events:

    date = event["event_dates"]["starting_day"][0:10]
    if date != current_date:

        current_date = date
        print("")
        print(current_date)
        print("----------")

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
