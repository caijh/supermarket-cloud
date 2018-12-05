import moment from 'moment'

export default function (date, format) {
  if (!date) return ''
  return moment(date).format(format || 'YYYY-MM-DD HH:mm:ss')
}
