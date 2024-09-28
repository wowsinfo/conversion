
interface TimeStampDateProps {
  timeStamp: number;
}

const makeDateTimeString = (date: Date): string => {
  return date.toLocaleString(); // Customize this format as needed
};

const TimeStampDate: React.FC<TimeStampDateProps> = ({ timeStamp }) => {
  if (timeStamp < 0) {
    throw new Error('timeStamp must be at least 0');
  }

  const date = new Date(timeStamp * 1000);
  const dateTimeString = makeDateTimeString(date);
  const dateString = dateTimeString.split(' ')[0];

  return (
    <View>
      <Text>Date: {dateString}</Text>
      <Text>DateTime: {dateTimeString}</Text>
    </View>
  );
};

export default TimeStampDate;

const makeDateTimeString = (date: Date): string => {
  const component = [
    date.getFullYear(),
    date.getMonth() + 1,
    date.getDate(),
    date.getHours(),
    date.getMinutes(),
    date.getSeconds()
  ].map(e => e.toString().padStart(2, '0'));
  
  return `${component[0]}.${component[1]}.${component[2]} ${component[3]}:${component[4]}:${component[5]}`;
};

class TimeStampDate {
  private timeStamp: number;
  private date: Date;

  constructor(date: Date) {
    this.date = date;
    this.timeStamp = date.getTime();
  }

  // Compare [date] with [other]
  compareTo(other: TimeStampDate): number {
    return this.timeStamp - other.timeStamp;
  }

  // Check if within [duration] from now
  isWithinDurationFromNow(duration: number): boolean {
    return this.isWithinDurationFrom(new Date(), duration);
  }

  // Check if within [duration] from [date]
  isWithinDurationFrom(begin: Date, duration: number): boolean {
    return this.date > new Date(begin.getTime() - duration);
  }

  // Check if [date] is before [other]
  isBefore(other: TimeStampDate): boolean {
    return this.date < other.date;
  }
}