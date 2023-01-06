type: CheckConfig
api_version: core/v2
metadata:
  name: check-disk-usage
  namespace: default
spec:
  command: >-
    check-disk-usage
    --include-fs-type "NTFS"
    --exclude-fs-path "C:,D:"
    --warning 90
    --critical 95
  subscriptions:
  - system
  runtime_assets:
  - sensu/check-disk-usage


api_version: core/v2
type: Handler
metadata:
  namespace: default
  name: email
spec:
  type: pipe
  command: sensu-email-handler -f <sender@example.com> -t <recipient@example.com> -s <smtp_server@example.com> -u username -p password
  timeout: 10
  filters:
  - is_incident
  - not_silenced
  - state_change_only
  runtime_assets:
  - email-handler
